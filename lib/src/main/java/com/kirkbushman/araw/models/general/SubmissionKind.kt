package com.kirkbushman.araw.models.general

/**
 * Enum class that defines the kind of submissions.
 * There are additional properties inside of Submission,
 * to specify whether it's a video, an image, if it's hosted on reddit.com domain or not...
 *
 * videogif,
 * video,
 * image,
 * link, which bears a link to media, images, websites
 * self, which is only a text post.
 */
enum class SubmissionKind {

    videogif,
    video,
    image,
    link,
    self
}
